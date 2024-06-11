import { faArrowRight } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { IAccountType } from "./AccountTypes"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"

interface AccountFormProps{
  accountType: IAccountType|null
}

export default function AccountForm({accountType} : AccountFormProps) {

  const navigate = useNavigate()

  const [overdraftLimit, setOverdraftLimit] = useState<string>("")
  const [interestRate, setInterestRate] = useState<string>("")
  const [investmentObjective, setInvestmentObjective] = useState<string>("")

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

  function validateCheckingAccountForm(){
    if(parseInt(overdraftLimit) < 0 || overdraftLimit.trim().length === 0){
      document.getElementById("overdraft limit error")?.classList.remove("invisible")
      setOverdraftLimit("")
      return false
    }
    return true
  }

  function validateSavingsAccountForm(){
    if(parseFloat(interestRate) < 0 || interestRate.trim().length === 0){
      document.getElementById("interest rate error")?.classList.remove("invisible")
      setInterestRate("")
      return false
    }
    return true
  }

  function validateInvestmentAccountForm(){
    if(investmentObjective.trim().length === 0){
      document.getElementById("investment objective error")?.classList.remove("invisible")
      setInvestmentObjective("")
      return false
    }
    return true
  }

  const handleAccountFormSubmit = async (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault()
    const parameters = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: ""
    }
    if(accountType?.name === "Checking account"){
      if(!validateCheckingAccountForm()){
        return
      }
      parameters.body = JSON.stringify({
        clientId: JSON.parse(localStorage.getItem("selectedClientId")!),
        type: "checking",
        overdraftLimit
      })
    }
    else if(accountType?.name === "Savings account"){
      if(!validateSavingsAccountForm()){
        return
      }
      parameters.body = JSON.stringify({
        clientId: JSON.parse(localStorage.getItem("selectedClientId")!),
        type: "savings",
        interestRate
      })
    }
    else if(accountType?.name === "Investment account"){
      if(!validateInvestmentAccountForm()){
        return
      }
      parameters.body = JSON.stringify({
        clientId: JSON.parse(localStorage.getItem("selectedClientId")!),
        type: "investment",
        investmentObjective
      })
    }
    const response = await fetch("http://localhost:8080/accounts", parameters)
    if(response.ok){
      localStorage.removeItem("selectedClientId")
      navigate("/")
    }
  }

  return (
    <form id="account form" className="flex flex-col w-fit relative left-1/4 top-1/2 -translate-y-1/2 hidden">
      <div id="checking account" className="flex flex-col">
        <label htmlFor="overdraft limit" className="font-semibold text-lg">What should be the overdraft limit?</label>
        <input id="overdraft limit" value={overdraftLimit}
        onClick={() => document.getElementById("overdraft limit error")?.classList.add("invisible")}
        onChange={(e) => setOverdraftLimit(e.currentTarget.value)}
        type="number" min="0" placeholder="Overdraft limit" className="mt-1 border-2 border-neutral-900 p-2"/>
        <p id="overdraft limit error" className="invisible text-sm text-red-500">Invalid value</p>
      </div>
      <div id="savings account" className="flex flex-col">
        <label htmlFor="interest rate" className="font-semibold text-lg">What should be the interest rate?</label>
        <input id="interest rate" value={interestRate}
        onClick={() => document.getElementById("interest rate error")?.classList.add("invisible")}
        onChange={(e) => setInterestRate(e.currentTarget.value)}
        type="number" min="0" placeholder="Interest rate" className="mt-1 border-2 border-neutral-900 p-2" step={0.01}/>
        <p id="interest rate error" className="invisible text-sm text-red-500">Invalid value</p>
      </div>
      <div id="investment account" className="flex flex-col">
        <label htmlFor="investment objective" className="font-semibold text-lg">What is the investment objective?</label>
        <input id="investment objective" value={investmentObjective}
        onClick={() => document.getElementById("investment objective error")?.classList.add("invisible")}
        onChange={(e) => setInvestmentObjective(e.currentTarget.value)}
        type="text" placeholder="Investment objective" className="mt-1 border-2 border-neutral-900 p-2"/>
        <p id="investment objective error" className="invisible text-sm text-red-500">Invalid value</p>
      </div>
      <button type="submit" onClick={handleAccountFormSubmit} className="mt-5 border-2 w-fit py-2 px-8 border-2 border-neutral-900">Open <FontAwesomeIcon icon={faArrowRight} /> </button>
    </form>
  )
}
