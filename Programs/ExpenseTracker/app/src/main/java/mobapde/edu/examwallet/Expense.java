package mobapde.edu.examwallet;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by courtneyngo on 1/31/16.
 */
public class Expense implements Parcelable{
    private String expenseName;
    private double price;
    private Date date;
    int position;

    public Expense(){}

    public Expense(double price, String expenseName) {
        this.price = price;
        this.expenseName = expenseName;
    }

    public Expense(String expenseName, double price, Date date) {
        this.expenseName = expenseName;
        this.price = price;
        this.date = date;
    }

    protected Expense(Parcel in) {
        expenseName = in.readString();
        price = in.readDouble();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expenseName);
        dest.writeDouble(price);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
