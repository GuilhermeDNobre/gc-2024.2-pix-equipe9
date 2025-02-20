import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Button } from "../ui/button"
import Link from "next/link"


export default function Register() {
    return (
        <div>
            <Card className="w-full max-w-md">
                <CardHeader>
                    <CardTitle className="text-2xl font-bold tracking-tighter">Cadastre-se</CardTitle>
                    <CardDescription className="text-sm">Insira seus dados</CardDescription>
                </CardHeader>
                <CardContent>
                    <div>
                        <div className="grid w-full max-w-sm items-center gap-1.5">
                            <Label htmlFor="email">Email</Label>
                            <Input type="email" id="email" placeholder="example@email.com" />
                        </div>
                        <div className="grid w-full max-w-sm items-center gap-1.5 mt-3">
                            <Label htmlFor="password">Password</Label>
                            <Input type="password" id="password" placeholder="password" />
                        </div>
                        <Button className="mt-4 w-full">Entrar</Button>
                    </div>

                </CardContent>
                <CardFooter>
                    <p>Não possui login? <Link href="./" className="text-purple-600">Cadastre-se</Link></p>
                </CardFooter>
            </Card>

        </div>
    )
}