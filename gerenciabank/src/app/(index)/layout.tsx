import type { Metadata } from "next";
import "../globals.css";
import { Carrousel } from "@/components/home/carrousel";
import { ReactNode } from "react";

export const metadata: Metadata = {
  title: "Gerência Bank",
  description: "",
};

type HeroLayoutProperties = {
  children: ReactNode;
}

export default function RootLayout({ children }: HeroLayoutProperties) {
  return (
    <html lang="en">
      <body
        className={`bg-zinc-50`}
      >
        <main className="h-screen w-full flex">
          <div className="h-full w-full p-16 m- flex justify-center items-center">
            <Carrousel />
          </div>
          <section className="flex h-full max-w-3xl w-full p-4 items-center justify-center">
            {children}
          </section>
        </main>
      </body>
    </html>
  );
}
