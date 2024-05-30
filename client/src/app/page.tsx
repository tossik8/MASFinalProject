import Customers from "@/components/Customers";


export default function Home() {

  return (
    <main className="w-full h-full max-w-screen-2xl flex justify-center items-center mx-auto">
      <section className="w-3/4 flex flex-col">
        <h1 className="text-center mb-20 text-4xl">Customers</h1>
        <div className="overflow-y-auto h-64">
            <Customers/>
        </div>
      </section>
    </main>
  );
}
