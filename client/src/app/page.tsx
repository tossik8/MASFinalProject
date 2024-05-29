import Image from "next/image";


export default function Home() {

  const customers = [
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
    <main className="w-full h-full max-w-screen-2xl flex justify-center items-center mx-auto">
      <section className="w-3/4">
        <h1 className="text-center mb-20 text-4xl">Customers</h1>
        <div className="overflow-auto h-72">
          <div className="grid grid-cols-4 gap-2 sticky top-0 bg-white">
            <label className="text-xl">First Name</label>
            <label className="text-xl">Last Name</label>
            <label className="text-xl">Email</label>
            <label className="text-xl">Password</label>
          </div>

          {customers.map((customer) =>
            <div className="grid grid-cols-4 gap-2 auto-rows-min padding-0 hover:bg-sky-500/75 hover: hover:cursor-pointer">
              <p className="overflow-x-auto">{customer.firstName}</p>
              <p className="overflow-x-auto">{customer.lastName}</p>
              <p className="overflow-x-auto">{customer.email}</p>
              <p className="overflow-x-auto">{customer.password}</p>
            </div>
          )}
        </div>
      </section>
    </main>
  );
}
