import Accounts from './Accounts'
import { IClient } from './Clients'
import React from 'react'

interface ClientCardProps{
  client: IClient,
  onSelectClient: (client: null) => void
}

const ClientCard = ({client, onSelectClient} : ClientCardProps) => {
  return (
    <div className="absolute bg-black/50 w-full h-full">
      <div className="relative w-2/3 bg-white top-1/2 left-1/2 -translate-y-1/2 -translate-x-1/2 p-8">
        <h2 className="text-3xl text-center">Client Information</h2>
        <button onClick={() => onSelectClient(null)} className='absolute right-4 top-4'>Close</button>

        <section className="mt-4 overflow-x-auto whitespace-nowrap">
          <h3 className="text-2xl">Personal Data</h3>
          <div className="flex gap-4 flex-wrap">
            <div>
              <p><b>First name:</b> {client.name}</p>
              <p><b>Last name:</b> {client.surname}</p>
            </div>
            <div>
              <p className=""><b>Email:</b> {client.credentials.email}</p>
              <p><b>Password:</b> {client.credentials.password}</p>
            </div>
          </div>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Branch</h3>
          <p>{`${client.registeredAt.name}, ${client.registeredAt.address}`}</p>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Accounts</h3>
          <div className="overflow-auto max-h-64">
              <Accounts accounts={client.accounts}/>
          </div>
        </section>
      </div>
    </div>
  )
}

export default ClientCard
