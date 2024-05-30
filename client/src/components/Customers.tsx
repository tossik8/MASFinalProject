import React from 'react'

export interface ICustomer{
  id: number
  firstName: string
  lastName: string
  email: string
  password: string
  accounts: IAccount[]
}

export interface IAccount{
  id: number
  accountNumber: string
  balance: number
  openingDate: Date
  status: string
  overdraftLimit?: number
  interestRate?: number
  investmentObjective?: string
}

interface CustomersProps{
  onSelectCustomer: (customer: ICustomer) => void
}

const Customers = ({onSelectCustomer} : CustomersProps) => {

  const customers: ICustomer[] = [
      {id: 1, firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678",
          accounts: [{id: 1, accountNumber: "dfgguyguygugguggujhghjjhjhghjga", openingDate: new Date(2023, 11), status: "ACTIVE", balance: 0, overdraftLimit: 0}]
      },
      {id: 2, firstName: "Mike", lastName: "Geller", email: "mgellfdsfdsfsfsfsfsdsfdsdfder@gmailc.com", password: "123456789",
          accounts: [{id: 2, accountNumber: "dfa", openingDate: new Date(2023, 11), status: "ACTIVE", balance: 0, interestRate: 0.05}]
      }
  ]

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
        {customers.map((customer: ICustomer) => (
          <tr key={customer.id} onClick={() => onSelectCustomer(customer)} className="hover:bg-blue-300 hover:cursor-pointer">
            <td>{customer.firstName}</td>
            <td>{customer.lastName}</td>
            <td>{customer.email}</td>
            <td>{customer.password}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default Customers
