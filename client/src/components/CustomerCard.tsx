import Accounts from './Accounts'
import { ICustomer } from './Customers'
import React from 'react'

interface CustomerCardProps{
  customer: ICustomer,
  onSelectCustomer: (customer: null) => void
}

const CustomerCard = ({customer, onSelectCustomer} : CustomerCardProps) => {
  return (
    <div className="absolute bg-black/50 w-full h-full">
      <div className="relative w-2/3 bg-white top-1/2 left-1/2 -translate-y-1/2 -translate-x-1/2 p-8">
        <h2 className="text-3xl text-center">Customer Information</h2>
        <button onClick={() => onSelectCustomer(null)} className='absolute right-4 top-4'>Close</button>

        <section className="mt-4 overflow-x-auto whitespace-nowrap">
          <h3 className="text-2xl">Personal Data</h3>
          <div className="flex gap-4 flex-wrap">
            <div>
              <p><b>First name:</b> {customer.firstName}</p>
              <p><b>Last name:</b> {customer.lastName}</p>
            </div>
            <div>
              <p className=""><b>Email:</b> {customer.email}</p>
              <p><b>Password:</b> {customer.password}</p>
            </div>
          </div>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Branch</h3>
          <p>{`${customer.registeredAt.name}, ${customer.registeredAt.address}`}</p>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Accounts</h3>
          <div className="overflow-auto max-h-64">
              <Accounts accounts={customer.accounts}/>
          </div>
        </section>
      </div>
    </div>
  )
}

export default CustomerCard
