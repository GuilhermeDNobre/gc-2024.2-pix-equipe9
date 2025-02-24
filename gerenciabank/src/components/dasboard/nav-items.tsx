"use client"

import { Banknote, SquareUser, PanelsTopLeft } from "lucide-react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { Button } from "../ui/button";
import { cn } from "@/lib/utils";

export const NavItems = () => {
    const pathname = usePathname();
    const navItems = [
        {
            label: "Overview",
            icon: PanelsTopLeft,
            path: "/dashboard/overview",
        },
        {
            label: "Transferências",
            icon: Banknote,
            path: "/dashboard/pix",
        },
        {
            label: "Configurações de Conta",
            icon: SquareUser,
            path: "/dashboard/account-settings",
        }
    ]

    return (
        <nav className="w-full flex flex-col gap-2 px-2 py-4">
            {navItems.map((item) => {
                const isActive = pathname.startsWith(item.path);
                return (
                    <Link
                        key={item.path}
                        href={item.path}
                    >
                        <Button
                            variant="ghost"
                            className={cn(
                                "w-full gap-2 justify-start",
                                isActive && "bg-accent"
                            )}>
                            <item.icon size={16} />
                            {item.label}
                        </Button>
                    </Link>
                )
            })}
        </nav>
    )
}
