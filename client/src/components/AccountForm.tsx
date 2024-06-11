import { faArrowRight } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { IAccountType } from "./AccountTypes"
import { useEffect } from "react"

interface AccountFormProps{
  accountType: IAccountType|null
}

export default function AccountForm({accountType} : AccountFormProps) {

  useEffect(() => {
    if(accountType){
      document.getElementById("account form")?.classList.remove("hidden")
      if(accountType.name === "Checking account"){
        document.getElementById("savings account")?.classList.add("hidden")
        document.getElementById("investment account")?.classList.add("hidden")
      }
      else if(accountType.name === "Savings account"){
        document.getElementById("checking account")?.classList.add("hidden")
        document.getElementById("investment account")?.classList.add("hidden")
      }
      else if(accountType.name === "Investment account"){
        document.getElementById("checking account")?.classList.add("hidden")
        document.getElementById("savings account")?.classList.add("hidden")
      }
    }
  }, [accountType])

  return (
    <form id="account form" className="flex flex-col w-fit relative left-1/4 top-1/2 -translate-y-1/2 hidden">
      <div id="checking account" className="flex flex-col">
        <label htmlFor="overdraft limit" className="font-semibold text-lg">What should be the overdraft limit?</label>
        <input id="overdraft limit" type="number" min="0" placeholder="Overdraft limit" className="mt-1 border-2 border-neutral-900 p-2"/>
      </div>
      <div id="savings account" className="flex flex-col">
        <label htmlFor="interest rate" className="font-semibold text-lg">What should be the interest rate?</label>
        <input id="interest rate" type="number" min="0" placeholder="Interest rate" className="mt-1 border-2 border-neutral-900 p-2"/>
      </div>
      <div id="investment account" className="flex flex-col">
        <label htmlFor="investment objective" className="font-semibold text-lg">What is the investment objective?</label>
        <input id="investment objective" type="text" placeholder="Investment objective" className="mt-1 border-2 border-neutral-900 p-2"/>
      </div>
      <button type="submit" className="mt-5 border-2 w-fit py-2 px-8 border-2 border-neutral-900">Open <FontAwesomeIcon icon={faArrowRight} /> </button>
    </form>
  )
}
