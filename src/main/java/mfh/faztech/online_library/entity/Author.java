package mfh.faztech.online_library.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author {
    protected int mId;
    protected String mName;
    private String mSecondName;
    private LocalDate mDateOfBirth;
    private Set<Integer> mBooks = new HashSet<>();


    public Author() {
    }

    public Author(int mId, String mName, String mSecondName, LocalDate mDateOfBirth) {
        this.mId = mId;
        this.mName = mName;
        this.mSecondName = mSecondName;
        this.mDateOfBirth = mDateOfBirth;
        this.mBooks = mBooks;
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

    public String getSecondName() {
        return mSecondName;
    }

    public void setSecondName(String mSecondName) {
        this.mSecondName = mSecondName;
    }

    public LocalDate getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(LocalDate mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public Set<Integer> getBooks() {
        return mBooks;
    }

    public void setBooks(Set<Integer> mBooks) {
        this.mBooks = mBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return mSecondName.equals(author.mSecondName) &&
                mDateOfBirth.equals(author.mDateOfBirth) &&
                mBooks.equals(author.mBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mSecondName, mDateOfBirth, mBooks);
    }

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", SecondName='" + mSecondName + '\'' +
                ", DateOfBirth=" + mDateOfBirth +
                ", Books=" + mBooks +
                '}';
    }
}
