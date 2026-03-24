import React from "react";

interface Props {
  name: string;
  code: string;
  price: string | null;
}

const StockCard: React.FC<Props> = ({ name, code, price }) => {
  return (
    <div style={styles.card}>
      <div style={styles.name}>{name}</div>
      <div style={styles.code}>{code}</div>
      <div style={styles.price}>
        {price ? `${Number(price).toLocaleString()}원` : "로딩 중..."}
      </div>
    </div>
  );
};

const styles: Record<string, React.CSSProperties> = {
  card: {
    background: "#1e1e2e",
    borderRadius: "12px",
    padding: "20px",
    width: "160px",
    textAlign: "center",
    boxShadow: "0 4px 12px rgba(0,0,0,0.3)",
  },
  name: {
    color: "#cdd6f4",
    fontSize: "16px",
    fontWeight: "bold",
    marginBottom: "4px",
  },
  code: {
    color: "#6c7086",
    fontSize: "12px",
    marginBottom: "12px",
  },
  price: {
    color: "#a6e3a1",
    fontSize: "20px",
    fontWeight: "bold",
  },
};

export default StockCard;
