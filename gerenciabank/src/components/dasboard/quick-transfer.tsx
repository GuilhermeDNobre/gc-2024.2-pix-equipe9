import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"

const recentContacts = [
  { id: 1, name: "Alice", avatar: "/placeholder.svg?height=32&width=32" },
  { id: 2, name: "Bob", avatar: "/placeholder.svg?height=32&width=32" },
  { id: 3, name: "Charlie", avatar: "/placeholder.svg?height=32&width=32" },
]

export default function QuickTransfer() {
  return (
    <Card>
      <CardHeader>
        <CardTitle>Quick Transfer</CardTitle>
      </CardHeader>
      <CardContent className="flex flex-col">
        <div className="flex space-x-4 mb-4 justify-evenly ">
          {recentContacts.map((contact) => (
            <Button key={contact.id} variant="outline" className="flex flex-col items-center p-2">
              <Avatar className="h-12 w-12 mb-2">
                <AvatarImage src={contact.avatar} alt={contact.name} />
                <AvatarFallback>{contact.name[0]}</AvatarFallback>
              </Avatar>
              <span className="text-xs">{contact.name}</span>
            </Button>
          ))}
        </div>
        <Button className="w-full mt-52">New Transfer</Button>
      </CardContent>
    </Card>
  )
}

