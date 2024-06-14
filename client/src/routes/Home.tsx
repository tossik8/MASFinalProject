import { useState } from "react";
import Clients, {IClient} from "../components/Clients";
import ClientCard from "../components/ClientCard";
import { IAccount } from "../components/Accounts";

export interface ISelectedClient extends IClient{
  registeredAt: {name: string, address: string}
  accounts: IAccount[]
}

export default function Home() {

  const [selectedClient, setSelectedClient] = useState<ISelectedClient|null>(null)

  const handleSelectClient = async (client: IClient|null) => {
    if(client === null){
      localStorage.removeItem("selectedClientId")
      setSelectedClient(null)
    }
    else{
      localStorage.setItem("selectedClientId", `${client.id}`)
      const response = await fetch(`http://localhost:8080/clients/${client.id}`)
      const data = await response.json()
      setSelectedClient(data)
    }
  }

  return (
    <main className="relative w-full h-full max-w-screen-4xl flex justify-center items-center mx-auto">
      <section className="w-3/4 flex flex-col">
        <h1 className="text-center mb-20 text-4xl">Clients</h1>
        <div className="overflow-y-auto max-h-64">
          <Clients onSelectClient={handleSelectClient}/>
        </div>
      </section>
      {selectedClient? <ClientCard
      client={selectedClient}
      onSelectClient={handleSelectClient}/> : null}
    </main>
  )
}
