package com.genius.wasylews.device.location;


import com.google.android.gms.tasks.Task;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

class GmsTaskMapper {

    public static <TResult> Observable<TResult> asObservable(final Task<TResult> task) {
        return Observable.create(emitter -> {
            task.addOnSuccessListener(tResult -> {
                emitter.onNext(tResult);
                emitter.onComplete();
            });

            task.addOnFailureListener(emitter::onError);
        });
    }

    public static <TResult> Flowable<TResult> asFlowable(final Task<TResult> task) {
        return asObservable(task).toFlowable(BackpressureStrategy.BUFFER);
    }

    public static <TResult> Single<TResult> asSingle(final Task<TResult> task) {
        return Single.create(emitter -> {
            task.addOnSuccessListener(emitter::onSuccess);
            task.addOnFailureListener(emitter::onError);
        });
    }

    public static <TResult> Completable asCompletable(final Task<TResult> task) {
        return Completable.create(emitter -> {
            task.addOnSuccessListener(tResult -> emitter.onComplete());
            task.addOnFailureListener(emitter::onError);
        });
    }
}
