import type { Metadata } from "next";
import localFont from "next/font/local";
import "./globals.css";
import { Carrousel } from "@/components/home/carrousel";

const geistSans = localFont({
  src: "./fonts/GeistVF.woff",
  variable: "--font-geist-sans",
  weight: "100 900",
});

export const metadata: Metadata = {
  title: "Gerência Bank",
  description: "",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} antialiased bg-zinc-50`}
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
