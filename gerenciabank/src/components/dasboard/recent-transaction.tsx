import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"

export default function RecentTransactions() {
  const transactions = [
    { id: 1, description: "Grocery Store", amount: -85.32, date: "2023-06-15" },
    { id: 2, description: "Salary Deposit", amount: 3500.0, date: "2023-06-14" },
    { id: 3, description: "Electric Bill", amount: -120.5, date: "2023-06-13" },
  ]

  return (
    <Card>
      <CardHeader>
        <CardTitle>Recent Transactions</CardTitle>
      </CardHeader>
      <CardContent>
        <ul className="space-y-2 mt-6">
          {transactions.map((transaction) => (
            <li key={transaction.id} className="flex justify-between items-center">
              <div>
                <p className="font-medium">{transaction.description}</p>
                <p className="text-sm text-muted-foreground">{transaction.date}</p>
              </div>
              <span className={transaction.amount > 0 ? "text-green-600" : "text-red-600"}>
                ${Math.abs(transaction.amount).toFixed(2)}
              </span>
            </li>
          ))}
        </ul>
      </CardContent>
    </Card>
  )
}

