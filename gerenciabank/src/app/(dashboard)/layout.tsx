import { ReactNode } from "react";
import { NavItems } from "@/components/dasboard/nav-items";
import { Landmark } from "lucide-react";
import { UserDropdown } from "@/components/dasboard/user-dropdown";


type DashboardLayoutProps = {
  children: ReactNode;
}

export default function DashboardLayout({ children }: DashboardLayoutProps) {
  return (
    <div className="w-full h-screen overflow-hidden grid grid-cols-[275px,1fr]">
      <aside className="w-full h-full flex flex-col items-center border-r border-muted">
        <div className="w-full p-6 border-b border-muted flex gap-2">
          <Landmark />
          <h1 className="text-lg text-bold">Gerência Bank</h1>
        </div>

        <NavItems />

        <div className="w-full mt-auto border-t border-muted px-3 py-4 flex items-center justify-between">
          <UserDropdown/>
        </div>
      </aside>
      <main className="p-6 flex flex-col w-full h-full overflow-auto">
        {children}
      </main>
    </div>
  )
}
