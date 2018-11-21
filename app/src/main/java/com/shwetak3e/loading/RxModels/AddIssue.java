package com.shwetak3e.loading.RxModels;

import com.shwetak3e.loading.model.Booking_1;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by shwetakumar on 8/8/17.
 * Add the Issue in the IssueVideoFragment and update the list in issue page.
 */

public class AddIssue {

    PublishSubject<Booking_1.Issue> subject=PublishSubject.create();

    public void setSubject(Booking_1.Issue issue) {
        subject.onNext(issue);
    }

    public Observable<Booking_1.Issue> getSubject(){
        return subject;
    }
}
