import { useState } from "react";
import Clients, {IClient} from "../components/Clients";
import ClientCard from "../components/ClientCard";

export default function Home() {

  const [selectedClient, setSelectedClient] = useState<IClient|null>(null)

  const handleSelectClient = (client: IClient|null) => {
    setSelectedClient(client)
    if(client === null){
      localStorage.removeItem("selectedClientId")
    }
    else{
      localStorage.setItem("selectedClientId", `${client.id}`)
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
