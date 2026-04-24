import traceback


class MappedException(Exception):

    def __init__(
            self,
            brief_public_message: str | None = None,
            details: str | None = None,
            original: Exception | None = None
    ):
        super().__init__(
            (f"{brief_public_message}") +
            (f" | Details: '{details}'" if details is not None else "") +
            (f" | Original: '{original}'" if original is not None else "")
        )
        self.brief_public_message = brief_public_message
        self.details = details
        self.original_exception = original

    @classmethod
    def with_brief_public_message(
            cls,
            brief_public_message: str,
            original_exception: Exception | None = None
    ):
        return cls(
            brief_public_message=brief_public_message,
            details=None,
            original=original_exception
        )

    @classmethod
    def with_full_details(
            cls,
            brief_public_message: str,
            details: str,
            original_exception: Exception | None = None
    ):
        return cls(
            brief_public_message=brief_public_message,
            details=details,
            original=original_exception
        )

    def get_original_exception_traceback_lines(
            self,
            limit: int) -> list[str]:
        if self.original_exception is not None:
            original_traceback = self.original_exception.__traceback__
            if original_traceback is None:
                return []
            lines = [line.rstrip("\n") for line in (
                traceback.format_tb(original_traceback)
            )]
            if len(lines) <= limit:
                return lines
            return lines[:limit] + [f"{len(lines) - limit} hidden line(s)"]
        return []
