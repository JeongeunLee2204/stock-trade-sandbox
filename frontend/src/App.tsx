import React, { useState, useEffect } from "react";
import StockCard from "./components/StockCard";
import TradePanel from "./components/TradePanel";
import { fetchCurrentPrice, fetchBalance } from "./api/kisApi";

const STOCKS = [
  { name: "삼성전자", code: "005930" },
  { name: "SK하이닉스", code: "000660" },
  { name: "NAVER", code: "035420" },
];

const App: React.FC = () => {
  const [prices, setPrices] = useState<Record<string, string | null>>({});
  const [balance, setBalance] = useState<string | null>(null);

  const loadPrices = async () => {
    const results: Record<string, string | null> = {};
    for (const stock of STOCKS) {
      try {
        results[stock.code] = await fetchCurrentPrice(stock.code);
        setPrices({ ...results });
      } catch {
        results[stock.code] = null;
      }
      await new Promise((resolve) => setTimeout(resolve, 1000));
    }
  };

  const loadBalance = async () => {
    try {
      const b = await fetchBalance();
      setBalance(b);
    } catch {
      setBalance(null);
    }
  };

  useEffect(() => {
    loadPrices();
    loadBalance();
    const interval = setInterval(loadPrices, 60000); // 1분마다
    return () => clearInterval(interval);
  }, []);

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>📈 Stock Trade Sandbox</h1>
      <div style={styles.balance}>
        💰 잔고:{" "}
        {balance ? `${Number(balance).toLocaleString()}원` : "로딩 중..."}
      </div>
      <div style={styles.cards}>
        {STOCKS.map((stock) => (
          <StockCard
            key={stock.code}
            name={stock.name}
            code={stock.code}
            price={prices[stock.code] ?? null}
          />
        ))}
      </div>
      <TradePanel onTrade={loadBalance} />
    </div>
  );
};

const styles: Record<string, React.CSSProperties> = {
  container: {
    minHeight: "100vh",
    background: "#181825",
    padding: "40px",
    fontFamily: "sans-serif",
  },
  title: {
    color: "#cdd6f4",
    fontSize: "28px",
    marginBottom: "16px",
  },
  balance: {
    color: "#f9e2af",
    fontSize: "18px",
    marginBottom: "24px",
  },
  cards: {
    display: "flex",
    gap: "16px",
    flexWrap: "wrap",
  },
};

export default App;
