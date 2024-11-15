"use client"
import React, { useEffect, useState } from 'react'

export default function Products() {
    const [products, setProducts] = useState([])
    
    useEffect(()=>{
        fetch("localhost:8080/api/v1/pizzas/getAll")
        .then(res => res.json())
        .then(data =>{
            console.log(data)
        })
    }, [])
  return (
    <div>
      
    </div>
  )
}
