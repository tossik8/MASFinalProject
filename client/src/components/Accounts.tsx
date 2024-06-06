export interface IAccount{
  id: number
  accountNumber: string
  balance: number
  openingDate: string
  status: string
  overdraftLimit?: number
  interestRate?: number
  investmentObjective?: string
}

interface AccountsProps{
  accounts: IAccount[]
}

const Accounts = ({accounts} : AccountsProps) => {
  return (
    <table className="table-auto min-w-max">
      <thead className="sticky top-0 bg-white">
        <tr>
          <th className="text-start border px-2">Account Number</th>
          <th className="text-start border px-2">Balance</th>
          <th className="text-start border px-2">Opening Date</th>
          <th className="text-start border px-2">Status</th>
          <th className="text-start border px-2">Overdraft Limit</th>
          <th className="text-start border px-2">Interest Rate</th>
          <th className="text-start border px-2">Investment Objective</th>
        </tr>
      </thead>
      <tbody>
        {accounts.map((account: IAccount) => (
          <tr key={account.id}>
            <td className="border px-2">{account.accountNumber}</td>
            <td className="border px-2">{account.balance}</td>
            <td className="border px-2">{account.openingDate}</td>
            <td className="border px-2">{account.status}</td>
            <td className="border px-2">{account.overdraftLimit}</td>
            <td className="border px-2">{account.interestRate}</td>
            <td className="border px-2">{account.investmentObjective}</td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default Accounts
