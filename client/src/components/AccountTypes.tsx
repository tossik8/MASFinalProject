export interface IAccountType{
  img: string
  name: string
  colour: string
}

interface AccountTypesProps{
  onSelectAccountType: (type: IAccountType) => void
}

function AccountTypes({onSelectAccountType} : AccountTypesProps) {

  const types: IAccountType[] = [
    {"img": "/checking.jpg", "name": "Checking account", "colour": "bg-[#FCF0D8]"},
    {"img": "/savings-basics.jpg", "name": "Savings account", "colour": "bg-[#FFDADA]"},
    {"img": "/investment.jfif", "name": "Investment account", "colour": "bg-[#DCFFDF]"}
  ]

  return (
    <div className="mt-5 flex gap-x-24">{types.map((type, index) => (
      <article key={index} onClick={() => onSelectAccountType(type)} className={`${type.colour}` + " p-3 shadow-lg shadow-slate-500 hover:cursor-pointer hover:scale-110 hover:ease-in duration-150"}>
        <img src={type.img} alt={type.name} className="object-cover h-56 w-60"/>
        <p className="mt-2 text-center text-lg">{type.name}</p>
      </article>
    ))}</div>
  )
}

export default AccountTypes
