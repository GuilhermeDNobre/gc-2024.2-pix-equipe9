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
import { Button } from "../ui/button"
import Link from "next/link"
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import * as z from "zod";
import { Label } from "../ui/label"
import UserService from "@/app/services/userService/userService"

const schema = z.object({
  name: z.string().min(3, "Nome deve ter pelo menos 3 caracteres"),
  email: z.string().email("E-mail inválido"),
  cpf: z.string().regex(/^[0-9]{11}$/, "CPF deve conter 11 dígitos numéricos"),
  password: z.string().min(6, "Senha deve ter pelo menos 6 caracteres"),
  birthDate: z.string().refine(
    (date) => {
      const birth = new Date(date);
      const today = new Date();
      const age = today.getFullYear() - birth.getFullYear();
      return age >= 18;
    },
    { message: "Você deve ter pelo menos 18 anos" }
  ),
});

export default function Register() {

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: zodResolver(schema),
  });

  const onSubmit = async (data) => {
    data.access = "USER"
    const result = await UserService.register(data)
    if(result?.success){
      window.location.href = '/login'
    } 
  };

  return (
    <div>
      <Card className="w-full max-w-md px-2 py-1">
        <CardHeader>
          <CardTitle className="text-2xl font-bold tracking-tighter">
            Cadastre-se
          </CardTitle>
          <CardDescription className="text-sm">
            Insira seus dados
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div>
              <Label htmlFor="name">Nome</Label>
              <Input {...register("name")} className="border p-2 w-full" id="name"/>
              {errors.name && <p className="text-red-500">{errors.name.message}</p>}
            </div>
            <div>
              <Label htmlFor="email">Email</Label>
              <Input {...register("email")} className="border p-2 w-full" type="email" id="email"/>
              {errors.email && <p className="text-red-500">{errors.email.message}</p>}
            </div>
            <div>
              <Label htmlFor="cpf">CPF</Label>
              <Input {...register("cpf")} className="border p-2 w-full" id="cpf"/>
              {errors.cpf && <p className="text-red-500">{errors.cpf.message}</p>}
            </div>
            <div>
              <Label htmlFor="password">Senha</Label>
              <Input {...register("password")} className="border p-2 w-full" type="password" id="password"/>
              {errors.password && <p className="text-red-500">{errors.password.message}</p>}
            </div>
            <div>
              <Label htmlFor="birthDate">Data de Nascimento</Label>
              <Input {...register("birthDate")} className="border p-2 w-full" type="date" id="birthDate"/>
              {errors.birthDate && <p className="text-red-500">{errors.birthDate.message}</p>}
            </div>
            <Button type="submit" className="mt-4 w-full text-lg">
              Criar conta
            </Button>
          </form>
        </CardContent>
        <CardFooter>
          <p>
            Já possui login?{" "}
            <Link href="/login" className="text-purple-600">
              Entre
            </Link>
          </p>
        </CardFooter>
      </Card>
    </div>
  );
}