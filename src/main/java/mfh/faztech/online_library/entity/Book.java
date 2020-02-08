package mfh.faztech.online_library.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Book {
    protected int mId;
    protected String mName;
    private String mDescription;
    private LocalDate mDateOfPublish;
    private Set<Integer> mAuthors = new HashSet<>();

    public Book() {
    }

    public Book(int mId, String mName, String mDescription, LocalDate mDateOfPublish) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mDateOfPublish = mDateOfPublish;
        this.mAuthors = mAuthors;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public LocalDate getDateOfPublish() {
        return mDateOfPublish;
    }

    public void setDateOfPublish(LocalDate mDateOfPublish) {
        this.mDateOfPublish = mDateOfPublish;
    }

    public Set<Integer> getAuthors() {
        return mAuthors;
    }

    public void setAuthors(Set<Integer> mAuthors) {
        this.mAuthors = mAuthors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return mDescription.equals(book.mDescription) &&
                mDateOfPublish.equals(book.mDateOfPublish) &&
                mAuthors.equals(book.mAuthors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mDescription, mDateOfPublish, mAuthors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Description='" + mDescription + '\'' +
                ", LocalDate=" + mDateOfPublish +
                ", Authors=" + mAuthors +
                '}';
    }
}
