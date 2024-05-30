"use client"

import CustomerCard from "@/components/CustomerCard";
import Customers, { ICustomer } from "@/components/Customers";
import { useState } from "react";


export default function Home() {

  const [selectedCustomer, setSelectedCustomer] = useState<ICustomer|null>()

  return (
    <main className="relative w-full h-full max-w-screen-2xl flex justify-center items-center mx-auto">
      <section className="w-3/4 flex flex-col">
        <h1 className="text-center mb-20 text-4xl">Customers</h1>
        <div className="overflow-y-auto max-h-64">
          <Customers onSelectCustomer={setSelectedCustomer}/>
        </div>
      </section>
      {selectedCustomer? <CustomerCard
      customer={selectedCustomer}
      onSelectCustomer={setSelectedCustomer}/> : null}
    </main>
  )
}
