const BASE_URL = "http://localhost:8080/kis";

export const fetchCurrentPrice = async (stockCode: string): Promise<string> => {
  await new Promise((resolve) => setTimeout(resolve, 500));
  const res = await fetch(`${BASE_URL}/price?stockCode=${stockCode}`);
  const data = await res.json();
  return data.output.stck_prpr;
};

export const fetchBalance = async (): Promise<string> => {
  const res = await fetch(
    `${BASE_URL}/balance?accountNo=50178507&productCode=01`,
  );
  const data = await res.json();
  return data.output2[0].prvs_rcdl_excc_amt;
};

export const buyStock = async (): Promise<string> => {
  const res = await fetch(`${BASE_URL}/trade/buy`, { method: "POST" });
  const data = await res.json();
  return data.message;
};

export const sellStock = async (): Promise<string> => {
  const res = await fetch(`${BASE_URL}/trade/sell`, { method: "POST" });
  const data = await res.json();
  return data.message;
};

export const triggerAutoTrading = async (): Promise<string> => {
  const res = await fetch(`${BASE_URL}/trade/trigger`, { method: "POST" });
  return res.text();
};
