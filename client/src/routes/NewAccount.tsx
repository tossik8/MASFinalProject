import { useState } from "react"
import AccountTypes, { IAccountType } from "../components/AccountTypes"
import AccountForm from "../components/AccountForm"

function NewAccount() {

  const [accountType, setAccountType] = useState<IAccountType|null>(null)

  return (
    <main className={"relative w-full h-full max-w-screen-4xl mx-auto " + `${accountType? "" : "flex items-center flex-col justify-center"}`}>
      {accountType? null :
      <>
        <h2 className="text-2xl">Choose account type</h2>
        <AccountTypes onSelectAccountType={setAccountType}/>
      </>}
      <AccountForm accountType={accountType} onSelectAccountType={setAccountType}/>
    </main>
  )
}

export default NewAccount
