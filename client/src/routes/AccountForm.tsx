import { useState } from "react"
import AccountTypes, { IAccountType } from "../components/AccountTypes"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faArrowRight } from "@fortawesome/free-solid-svg-icons"

interface IAccountForm{
  label: string
  placeholder: string
  type: string
  min?: number
  minLength?: number
}

function AccountForm() {

  const [accountForm, setAccountForm] = useState<IAccountForm>()

  const handleSelectAccountType = (type: IAccountType) => {
    if(type.name === "Checking account"){
      setAccountForm({
        "label": "What should be the overdraft limit?",
        "placeholder": "Overdraft limit",
        "type": "number",
        "min": 0
      })
    }
    else if(type.name === "Investment account"){
      setAccountForm({
        "label": "What is the investement objective?",
        "placeholder": "Investment objective",
        "type": "text",
        "minLength": 1
      })
    }
    else{
      const selectedClientId = JSON.parse(localStorage.getItem("selectedClientId")!)
    }
  }

  return (
    <main className={"relative w-full h-full max-w-screen-4xl mx-auto " + `${accountForm? "" : "flex items-center flex-col justify-center"}`}>
      {accountForm?
      <form className="flex flex-col w-fit relative left-1/4 top-1/2 -translate-y-1/2">
        <label htmlFor={accountForm.label} className="font-semibold text-lg">{accountForm.label}</label>
        <input id={accountForm.label} type={accountForm.type} min={accountForm.min} minLength={accountForm.minLength} placeholder={accountForm.placeholder} className="mt-4 border-2 border-neutral-900 p-2"/>
        <button type="submit" className="mt-4 border-2 w-fit py-2 px-8 border-2 border-neutral-900">Open <FontAwesomeIcon icon={faArrowRight} /> </button>
      </form> :
      <>
        <h2 className="text-2xl">Choose account type</h2>
        <AccountTypes onSelectAccountType={handleSelectAccountType}/>
      </>}
    </main>
  )
}

export default AccountForm
