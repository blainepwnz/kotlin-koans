package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate) = date >= start && date <= endInclusive
    operator fun iterator() =
            object : Iterator<MyDate> {
                var curr: MyDate = start
                override fun hasNext() = curr <= endInclusive
                override fun next(): MyDate {
                    val result = curr
                    curr = curr.nextDay()
                    return result
                }
            }
}

operator fun MyDate.plus(interval: TimeInterval) = addTimeIntervals(interval, 1)

operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate {
    var result = this
    for (i in 1..interval.n) {
        result+=interval.ti
    }
    return result
}

operator fun TimeInterval.times(count: Int) = RepeatedTimeInterval(this, count)


operator fun MyDate.compareTo(other: MyDate): Int {
    if (year != other.year)
        return year - other.year
    if (month != other.month)
        return month - other.month
    return dayOfMonth - other.dayOfMonth
}