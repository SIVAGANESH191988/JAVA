
public class Transaction {
private int trnxnId;
private double trnxnAmt;
private TransactionSource Trnxsrc;
private long Srcnumber;
private TransactionDestination TrnxDest;
private long Destnumber;

public void setTrnxnId(int trnxnId) {
	this.trnxnId = trnxnId;
}
public int getTrnxnId() {
	return trnxnId;
}
public double getTrnxnAmt() {
	return trnxnAmt;
}
public void setTrnxnAmt(double trnxnAmt) {
	this.trnxnAmt = trnxnAmt;
}
public TransactionSource getTrnxsrc() {
	return Trnxsrc;
}
public void setTrnxsrc(TransactionSource trnxsrc) {
	Trnxsrc = trnxsrc;
}
public long getSrcnumber() {
	return Srcnumber;
}
public void setSrcnumber(long srcnumber) {
	Srcnumber = srcnumber;
}
public TransactionDestination getTrnxDest() {
	return TrnxDest;
}
public void setTrnxDest(TransactionDestination trnxDest) {
	TrnxDest = trnxDest;
}
public long getDestnumber() {
	return Destnumber;
}
public void setDestnumber(long destnumber) {
	Destnumber = destnumber;
}
public String toString() {
	return this.trnxnId+":"+ this.trnxnAmt +":"+ this.Trnxsrc + ":"+this.TrnxDest+"";
}
}
