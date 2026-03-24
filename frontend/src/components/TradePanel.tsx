import React, { useState } from "react";
import { buyStock, sellStock, triggerAutoTrading } from "../api/kisApi";

interface Props {
  onTrade: () => void;
}

const TradePanel: React.FC<Props> = ({ onTrade }) => {
  const [result, setResult] = useState<string>("");
  const [loading, setLoading] = useState(false);

  const handle = async (action: () => Promise<string>) => {
    setLoading(true);
    setResult("");
    try {
      const msg = await action();
      setResult(msg ?? "완료");
      await new Promise((resolve) => setTimeout(resolve, 1000)); // 1초 대기
      onTrade();
    } catch (e) {
      setResult("오류 발생");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.panel}>
      <h3 style={styles.title}>삼성전자 (005930)</h3>
      <div style={styles.buttons}>
        <button style={styles.buy} onClick={() => handle(buyStock)}>
          🟢 매수 1주
        </button>
        <button style={styles.sell} onClick={() => handle(sellStock)}>
          🔴 매도 1주
        </button>
        <button
          style={styles.trigger}
          onClick={() => handle(triggerAutoTrading)}
        >
          🔍 자동매매 조건 체크
        </button>
      </div>
      {loading && <div style={styles.result}>Loading...</div>}
      {result && <div style={styles.result}>{result}</div>}
    </div>
  );
};

const styles: Record<string, React.CSSProperties> = {
  panel: {
    background: "#1e1e2e",
    borderRadius: "12px",
    padding: "24px",
    marginTop: "24px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.3)",
  },
  title: {
    color: "#cdd6f4",
    marginBottom: "16px",
  },
  buttons: {
    display: "flex",
    gap: "12px",
    flexWrap: "wrap",
  },
  buy: {
    padding: "10px 20px",
    background: "#a6e3a1",
    border: "none",
    borderRadius: "8px",
    fontWeight: "bold",
    cursor: "pointer",
  },
  sell: {
    padding: "10px 20px",
    background: "#f38ba8",
    border: "none",
    borderRadius: "8px",
    fontWeight: "bold",
    cursor: "pointer",
  },
  trigger: {
    padding: "10px 20px",
    background: "#89b4fa",
    border: "none",
    borderRadius: "8px",
    fontWeight: "bold",
    cursor: "pointer",
  },
  result: {
    marginTop: "16px",
    color: "#cdd6f4",
    background: "#313244",
    borderRadius: "8px",
    padding: "12px",
  },
};

export default TradePanel;
