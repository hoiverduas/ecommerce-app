import React from 'react'
import {PT_Sans_Narrow} from 'next/font/google'

const ptSansNarrow = PT_Sans_Narrow({
    subsets:['latin'],
    weight: ["400", "700"],
    variable: "--pt-sans-narrow"
  })

export default function Hero() {
  return (
    <section className='flex flex-col items-center relative'>
        <h2 className={`${ptSansNarrow.className} text-6xl lg:text-7xl xl:text-8xl  2xl:text-[120px] text-white tracking-tighter font-bold m-0 p-0 leading-tight mt-5 xl:mt-7 2xl:mt-16`}>COMIDA <span className='text-[#FF6A00]'>DE</span> CALIDAD</h2>
        <p className={`${ptSansNarrow.className} text-lg lg:text-2xl tracking-widest text-[#FF6A00]`}>HEALTHY FOOD FOR HEALTHY PEOPLE</p>
        <img className='w-9/12 mt-8 lg:mt-4' src="pizza.png" alt="" />
        <div className="absolute w-full bg-[#fe9d3e] -bottom-40 px-4 md:px-6 lg:px-12 xl:px-20 2xl:px-40">
            <div className='w-full'>
                <img className='rotate-180 -translate-y-2/3' src="path.png" alt="" />
                <div className='w-full flex items-center justify-between'>
                    <div className='flex flex-col items-center w-4/12  px-4 lg:px-7'>
                        <img className='w-24' src="plate.svg" alt="" />
                        <h3 className='font-bold text-2xl'>PEDIDO ONLINE</h3>
                        <p className='text-center mt-4'>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-</p>
                    </div>

                    <div className='flex flex-col items-center w-4/12  px-4 lg:px-7'>
                        <img className='w-24' src="delivery.svg" alt="" />
                        <h3 className='font-bold text-2xl'>PEDIDO ONLINE</h3>
                        <p className='text-center mt-4'>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-</p>
                    </div>

                    <div className='flex flex-col items-center w-4/12 '>
                        <img className='w-24' src="pizz.svg" alt="" />
                        <h3 className='font-bold text-2xl'>PEDIDO ONLINE</h3>
                        <p className='text-center mt-4'>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eius-</p>
                    </div>

                </div>
                <img className='translate-y-[49%]' src="path.png" alt="" />
            </div>
        </div>

    </section>
  )
}
