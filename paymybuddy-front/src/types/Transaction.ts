export interface Transaction {
    senderUsername: string | null;
    receiverUsername: string | null;
    description: string;
    amount: number;
}