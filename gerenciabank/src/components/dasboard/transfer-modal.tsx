"use client"

import { useState } from "react"
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
  } from "@/components/ui/dialog"
import { Button } from "../ui/button"
import { Label } from "../ui/label"
import { Input } from "../ui/input"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"

export function TransferModal() {
  const [open, setOpen] = useState(false)
  const [method, setMethod] = useState("")
  const [amount, setAmount] = useState("")

  const handleTransfer = () => {
    // Here you would implement the actual transfer logic
    console.log(`Transferring ${amount} via ${method}`)
    setOpen(false)
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>
        <Button variant="default">New Transfer</Button>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Make a Transfer</DialogTitle>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="transfer-method" className="text-right">
              Method
            </Label>
            <Select onValueChange={setMethod} value={method}>
              <SelectTrigger className="col-span-3" id="transfer-method">
                <SelectValue placeholder="Select transfer method" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="email">Email</SelectItem>
                <SelectItem value="cpf">CPF</SelectItem>
                <SelectItem value="aleatorio">Aleatório</SelectItem>
                <SelectItem value="phone">Phone Number</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="amount" className="text-right">
              Amount
            </Label>
            <Input
              id="amount"
              type="number"
              placeholder="Enter amount"
              className="col-span-3"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
            />
          </div>
        </div>
        <Button onClick={handleTransfer} disabled={!method || !amount}>
          Transfer
        </Button>
      </DialogContent>
    </Dialog>
  )
}

