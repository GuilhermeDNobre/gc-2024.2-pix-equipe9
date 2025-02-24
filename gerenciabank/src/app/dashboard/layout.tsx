import { ReactNode } from "react";


type DashboardLayoutProps = {
    children: ReactNode;    
}

export default function DashboardLayout({children}: DashboardLayoutProps) {
  return (
    <html lang="en">
          <body
            className={` bg-zinc-50`}
          >
            {children}
          </body>
        </html>
  );
}
