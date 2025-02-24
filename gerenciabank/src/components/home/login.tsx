"use client"

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
import { useState } from "react"
import userService from "@/app/services/userService/userService"


export default function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async () => {
        console.log(email + " " + password)
        const result = await userService.login(email, password);
        if (result?.success) {
            // Redirecionar para a página principal ou realizar outra ação
            window.location.href = '/dashboard';
        } else {
            setError(result.message);
        }
    };


    return (
        <div>
            <Card className="w-full max-w-md">
                <CardHeader>
                    <CardTitle className="text-2xl font-bold tracking-tighter">Faça login</CardTitle>
                    <CardDescription className="text-sm">Insira seu email e sua senha</CardDescription>
                </CardHeader>
                <CardContent>
                    <div>
                        <div className="grid w-full max-w-sm items-center gap-1.5">
                            <Label htmlFor="email">Email</Label>
                            <Input 
                                type="email" 
                                id="email" 
                                placeholder="example@email.com" 
                                value={email} 
                                onChange={(event) => setEmail(event.target.value)} 
                                required/>
                        </div>
                        <div className="grid w-full max-w-sm items-center gap-1.5 mt-3">
                            <Label htmlFor="password">Password</Label>
                            <Input 
                                type="password" 
                                id="password" 
                                placeholder="password" 
                                value={password} 
                                onChange={(event) => setPassword(event.target.value)} 
                                required/>
                        </div>
                        {error && 
                            <p className="text-red-600 m-2">{error}</p>
                        }
                        <Button type="submit" onClick={handleSubmit} className="mt-4 w-full text-lg">Entrar</Button>
                    </div>
                    

                </CardContent>
                <CardFooter>
                    <p>Não possui login? <Link href="register" className="text-purple-600">Cadastre-se</Link></p>
                </CardFooter>
            </Card>

        </div>
    )
}