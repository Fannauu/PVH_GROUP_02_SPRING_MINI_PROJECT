import Image from "next/image"
import React from 'react'
import LogoNum from '../../../../../../public/image-removebg-preview.png'
import { UserProfile } from './UserProfile'

function Navbar() {
  return (
    <div className='w-full bg-white z-50 py-4 px-20 max-sm:px-10 flex justify-between shadow-md fixed top-0 '>
      <div className='w-[70px] h-[70px] max-sm:w-[40px] max-sm:h-[40px] '>
        <div className="flex justify-center items-center gap-3 max-sm:gap-2">
          <Image
            className="max-sm:w-[40px] max-sm:h-[40px]"
            width={70}
            height={70}
            src={LogoNum}
            alt='website logo' />
          <span className="
            text-3xl max-sm:text-xl 
            font-bold 
            bg-gradient-to-r from-blue-600 via-purple-600 to-blue-800 
            bg-clip-text text-transparent 
            tracking-wider 
            select-none 
            transform hover:scale-105 
            transition-all duration-300 
            font-sans 
            drop-shadow-sm
            uppercase
            letter-spacing-[0.1em]
          ">
            NUM
          </span>
        </div>

      </div>
      <UserProfile />
    </div>
  )
}

export default Navbar