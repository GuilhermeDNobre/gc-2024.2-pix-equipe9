import BalanceHistory from "@/components/dasboard/balance-history";
import CurrentBalance from "@/components/dasboard/current-balance";
import QuickTransfer from "@/components/dasboard/quick-transfer";
import RecentTransactions from "@/components/dasboard/recent-transaction";
import WeeklySpending from "@/components/dasboard/weekly-spending";


export default function MainOverview() {
  return (
    <div className="w-full h-full flex flex-col gap-7">
      <h1 className="text-3xl font-bold mb-6">Dashboard</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <CurrentBalance />
        <RecentTransactions />
      </div>
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <WeeklySpending />
        <QuickTransfer />
      </div>
      <BalanceHistory />
    </div>
  );
}
