package practicalWork3;

//Створіть наступну модель: Є багатоквартирний дім, модель повинна дозволяти прописувати/виписувати  мешканців,
//нараховувати квартплату в залежності від площі квартир, давати можливість сплачувати мешканцям квартплату,
//формувати звіт з платежів та заборгованості. Якщо виникає заборгованість та її термін перевищує місяць з моменту виникнення,
//повинен нараховуватись штраф в розмірі 10 відсотків від суми її суми. Квартплатня нараховується першого числа кожного місяця.
//Звіти повинні сортуватися та фільтруватися за разними критеріями.

import java.util.ArrayList;

public class FlatHouse {
    Flat[] flats;
    ArrayList<PaymentReport> reports = new ArrayList<PaymentReport>();
    public FlatHouse(Flat[] flats) {
        this.flats = flats;
        int counter = 1;
        for(Flat flat : flats) {
            flat.index = counter++;
        }
    }
    public void register(Resident resident, int index) {
        flats[index-1].register(resident);
    }
    public void unregister(int index) {
        flats[index-1].unregister();
    }
    public void showReports() {
        for(PaymentReport p : reports) {
            p.showReport();
        }
    }
    public void creditRent() {
        for(Flat flat : flats) {
            flat.creditRent();
        }
    }
}
