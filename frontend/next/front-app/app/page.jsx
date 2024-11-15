import Image from "next/image";
import Navbar from "./components/Navbar";
import Hero from "./components/Hero";
import Products from "./components/Products";

export default function Home() {
  return (
    <main>
      <div className="hero bg-black">
        <Navbar />
        <Hero />
      </div>
      <Products />
    </main>
  );
}
