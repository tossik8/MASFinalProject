import React, { useEffect, useState } from 'react'
import { IAccount } from './Accounts'

export interface IClient{
  id: number
  name: string
  surname: string
  credentials: {email: string, password: string}
  registeredAt: {id: number, name: string, address: string}
  accounts: IAccount[]
}

interface ClientsProps{
  onSelectClient: (customer: IClient) => void
}

const Clients = ({onSelectClient} : ClientsProps) => {

  const [clients, setClients] = useState<IClient[]>()

  useEffect(() => {
    async function fetchClients(){
      const response = await fetch("http://localhost:8080/clients")
      const data = await response.json()
      setClients(data)
    }
    fetchClients()
  }, [])

  return (
    <table className="table-auto min-w-full w-max">
      <thead className="sticky top-0 bg-white">
        <tr>
            <th className="text-start">First Name</th>
            <th className="text-start">Last Name</th>
            <th className="text-start">Email</th>
            <th className="text-start">Password</th>
        </tr>
      </thead>
      <tbody>
        {clients? clients.map((client: IClient) => (
          <tr key={client.id} onClick={() => onSelectClient(client)} className="hover:bg-blue-300 hover:cursor-pointer">
            <td>{client.name}</td>
            <td>{client.surname}</td>
            <td>{client.credentials.email}</td>
            <td>{client.credentials.password}</td>
          </tr>
        )) : null}
      </tbody>
    </table>
  )
}

export default Clients
