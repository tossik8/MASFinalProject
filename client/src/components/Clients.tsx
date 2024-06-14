import { useEffect, useState } from 'react'

export interface IClient{
  id: number
  name: string
  surname: string
  email: string
}

interface ClientsProps{
  onSelectClient: (client: IClient) => void
}

const Clients = ({onSelectClient} : ClientsProps) => {

  const [clients, setClients] = useState<IClient[]>(null!)

  useEffect(() => {
    async function fetchClients(){
      const response = await fetch("http://localhost:8080/clients")
      const data = await response.json()
      setClients(data)
    }
    fetchClients()
  }, [])

  return (
    <table className="table-fixed min-w-full w-full">
      <thead className="sticky top-0 bg-white">
        <tr>
            <th className="text-start">First Name</th>
            <th className="text-start">Last Name</th>
            <th className="text-start">Email</th>
        </tr>
      </thead>
      <tbody>
        {clients?.map((client: IClient) => (
          <tr key={client.id} onClick={() => onSelectClient(client)} className="hover:bg-blue-300 hover:cursor-pointer">
            <td>{client.name}</td>
            <td>{client.surname}</td>
            <td>{client.email}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default Clients
