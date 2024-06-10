import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./routes/Home";
import AccountForm from "./routes/AccountForm";

export default function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/account_form" element={<AccountForm/>}/>
      </Routes>
    </BrowserRouter>
  )
}
