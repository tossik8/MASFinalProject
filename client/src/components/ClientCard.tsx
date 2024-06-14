import { useRef } from 'react'
import Accounts from './Accounts'
import { useNavigate } from 'react-router-dom'
import { ISelectedClient } from '../routes/Home'

interface ClientCardProps{
  client: ISelectedClient,
  onSelectClient: (client: null) => void
}

const ClientCard = ({client, onSelectClient} : ClientCardProps) => {

  const navigate = useNavigate()

  const [closeButton, outerDiv] = [useRef<HTMLButtonElement>(null), useRef<HTMLDivElement>(null)]

  const handleCloseClick = (e: React.MouseEvent<HTMLDivElement|HTMLButtonElement>) => {
    e.stopPropagation()
    if(e.target === closeButton.current ||
      e.target === outerDiv.current){
      onSelectClient(null)
    }
  }

  return (
    <div ref={outerDiv} className="absolute bg-black/50 w-full h-full" onClick={(handleCloseClick)}>
      <div className="relative flex flex-col w-2/3 bg-white top-1/2 left-1/2 -translate-y-1/2 -translate-x-1/2 p-8">
        <h2 className="text-3xl text-center">Client Information</h2>
        <button ref={closeButton} type="button" onClick={handleCloseClick} className='absolute right-4 top-4 bg-neutral-900 text-white p-2'>Close</button>

        <section className="mt-4 overflow-x-auto whitespace-nowrap">
          <h3 className="text-2xl">Personal Data</h3>
          <div className="flex gap-4 flex-wrap">
            <div>
              <p><span className="font-medium">First name:</span> {client.name}</p>
              <p><span className="font-medium">Last name:</span> {client.surname}</p>
            </div>
            <div>
              <p><span className="font-medium">Email:</span> {client.email}</p>
            </div>
          </div>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Branch</h3>
          <p>{`${client.registeredAt.name}, ${client.registeredAt.address}`}</p>
        </section>

        <section className="mt-4">
          <h3 className="text-2xl">Accounts</h3>
          <div className="overflow-auto max-h-64">
              <Accounts accounts={client.accounts}/>
          </div>
        </section>
        <button type="button" onClick={() => navigate("/new_account")} className="p-2 bg-neutral-900 text-white relative left-1/2 -translate-x-1/2 mt-4 w-fit">+ Add account</button>
      </div>
    </div>
  )
}

export default ClientCard
