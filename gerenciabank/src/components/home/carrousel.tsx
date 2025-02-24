"use client"
import Autoplay from 'embla-carousel-autoplay'
import {
  Carousel,
  CarouselContent,
  CarouselItem,
} from "@/components/ui/carousel"

import Amico1Icon from "../../assets/Investment data-amico.svg";
import Amico2Icon from "../../assets/Investment data-bro.svg";
import Amico3Icon from "../../assets/Revenue-amico.svg";
import Amico4Icon from "../../assets/Wallet-amico.svg";

export function Carrousel() {
  
  return (
    <Carousel className="w-full max-h-xl" 
    
    plugins={[
      Autoplay({
        delay: 3000,
      }),
    ]}
    
    >
      <CarouselContent>
        <CarouselItem className="flex aspect-square bg-background rounded">
          <Amico1Icon />
        </CarouselItem>
        <CarouselItem className="flex aspect-square bg-background rounded">
          <Amico2Icon />
        </CarouselItem>
        <CarouselItem className="flex aspect-square bg-background rounded">
          <Amico3Icon />
        </CarouselItem>
        <CarouselItem className="flex aspect-square bg-background rounded">
          <Amico4Icon />
        </CarouselItem>
      </CarouselContent>
    </Carousel>
  )
}