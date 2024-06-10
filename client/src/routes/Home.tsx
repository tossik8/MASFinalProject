import { useState } from "react";
import Clients, {IClient} from "../components/Clients";
import ClientCard from "../components/ClientCard";

export default function Home() {

  const [selectedClient, setSelectedClient] = useState<IClient|null>()

  return (
    <main className="relative w-full h-full max-w-screen-4xl flex justify-center items-center mx-auto">
      <section className="w-3/4 flex flex-col">
        <h1 className="text-center mb-20 text-4xl">Clients</h1>
        <div className="overflow-y-auto max-h-64">
          <Clients onSelectClient={setSelectedClient}/>
        </div>
      </section>
      {selectedClient? <ClientCard
      client={selectedClient}
      onSelectClient={setSelectedClient}/> : null}
    </main>
  )
}
