package com.shwetak3e.loading.RxModels;

import com.shwetak3e.loading.model.Booking_1;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by shwetakumar on 8/8/17.
 */

public class UpdateShipmentID {
    PublishSubject<Booking_1.Item>subject=PublishSubject.create();

    public void setSubject(Booking_1.Item item) {
        subject.onNext(item);
    }

    public Observable<Booking_1.Item> getSubject(){
        return subject;
    }

}
