import React from 'react'
import {Caveat} from "next/font/google"

const caveat = Caveat({
    subsets:["latin"],
    weight:["400", "600", "700"]
})

export default function Navbar() {
  return (
    <nav className='text-white flex items-center justify-between px-4 md:px-6 lg:px-12 xl:px-20 2xl:px-40 py-1'>
        {/* Navbar Logo */}
        <div>
            <p className={`${caveat.className} text-3xl lg:text-5xl xl:text-7xl font-semibold`}>Pizz <span className='m-0 text-[#FF6A00]'>art</span> </p>
        </div>
        {/* Navbar options */}
        <div className='hidden lg:block'>
            <ul className='flex gap-4 2xl:gap-8'>
                <li>HOME</li>
                <li>MENU</li>
                <li>CONTACT</li>
                <li>ABOUT US</li>
            </ul>
        </div>
        {/* order and cart */}
        <div>
            <button className='bg-[#FF6A00] px-5 xl:px-7 py-3 rounded-full hover:brightness-125 font-semibold'>REALIZAR PEDIDO</button>
        </div>
    </nav>
  )
}
