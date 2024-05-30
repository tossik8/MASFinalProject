import React from 'react'

export interface ICustomer{
    firstName: string
    lastName: string
    email: string
    password: string
}

const Customers = () => {

    const customers: ICustomer[] = [
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"},
        {firstName: "Mike", lastName: "Geller", email: "mgeller@gmailc.com", password: "12345678"}
    ]

    return (
        <table className="table-fixed min-w-full">
            <thead className="sticky top-0 bg-white">
                <tr>
                    <th className="text-start w-1/4">First Name</th>
                    <th className="text-start w-1/4">Last Name</th>
                    <th className="text-start w-1/4">Email</th>
                    <th className="text-start w-1/4">Password</th>
                </tr>
            </thead>
            <tbody>
                {customers.map((customer: ICustomer) => (
                    <tr className="hover:bg-blue-300 hover:cursor-pointer">
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
