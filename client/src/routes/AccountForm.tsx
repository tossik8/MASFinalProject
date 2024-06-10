import AccountTypes from "../components/AccountTypes"

function AccountForm() {
  return (
    <main className="relative w-full h-full max-w-screen-4xl flex flex-col justify-center items-center mx-auto">
      <h2 className="text-2xl">Choose account type</h2>
      <AccountTypes/>
    </main>
  )
}

export default AccountForm
