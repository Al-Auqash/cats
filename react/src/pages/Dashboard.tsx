import { Route, Routes } from "react-router-dom";
import Navbar from "../components/dashboard/Navbar";
import Home from "../components/dashboard/Home";
import Sidebar from "../components/dashboard/Sidebar";

const Dashboard = () => {
  return (
    <div className="flex flex-row">
      <div className="flex flex-col basis-[10%]">
        <Sidebar />
      </div>
      <div className="flex flex-col basis-[90%]">
        <Navbar />
        <Routes>
          <Route path='/' element={<Home />} />
        </Routes>
      </div>
    </div>
  )
};

export default Dashboard;
