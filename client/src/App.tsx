import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./routes/Home";
import NewAccount from "./routes/NewAccount";

export default function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/new_account" element={<NewAccount/>}/>
      </Routes>
    </BrowserRouter>
  )
}
