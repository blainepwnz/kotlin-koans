package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)

operator fun DateRange.contains(date: MyDate) : Boolean{
    return date >= start && date <= endInclusive
}

operator fun MyDate.compareTo(other: MyDate): Int {
    if(year != other.year)
        return year - other.year
    if(month != other.month)
        return month - other.month
    return dayOfMonth - other.dayOfMonth
}